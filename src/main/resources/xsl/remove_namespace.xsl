<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8"
		indent="yes" />

	<xsl:template match="*">
		<xsl:element name="{local-name()}">
			<xsl:for-each select="@*">
				<xsl:attribute name="{local-name()}">
          <xsl:value-of select="." />
        </xsl:attribute>
			</xsl:for-each>
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>